WIP - custom kernel for android emulator

-> purpose is to be able to log syscalls directly from kernel in order to detect suspicious behaviour

################################################################################################################################
Build the kernel

make ARCH=x86_64 x86_64_ranchu_defconfig
make -j16 ARCH=x86_64 CROSS_COMPILE=x86_64-linux-android-


################################################################################################################################
Start emulator with custom kernel

Go under Android/Sdk/emulator folder and launch the emulator as follows:
./emulator -verbose @AVD_NAME -kernel /path/to/repo/goldfish/arch/x86/boot/bzImage -show-kernel -qemu — enable-kvm


/* e.g.

./emulator -verbose @tutorial1 -kernel /home/luci/Desktop/thesis/tutorials/tutorial1/goldfish/arch/x86/boot/bzImage -show-kernel -qemu -enable-kvm -read-only

*/


################################################################################################################################

Run Test app provided in order to test the accuracy of the logs

The TestApp is a simple app that is designed to perform basic fs operations (open, read, write, close). The kernel logs should catch the app's syscalls.
Install the app in the emulator and run it by the following commands:
adb -s emulator-5553 install path_to_apk
adb -s emulator-5554 shell am start -n com.example.testapp/.MainActivity

/* e.g. 

adb -s emulator-5553 install /home/luci/Desktop/thesis/tutorials/tutorial1/TestApp/app/build/outputs/apk/debug/app-debug.pk
adb -s emulator-5554 shell am start -n com.example.testapp/.MainActivity

*/


################################################################################################################################


CURRENT STATUS

Hook implementation that traces the a syscall. The function can be simplty attached to any syscall in order to log its behaviour.

The signature of the hook is 
hook(syscall_name, argument_descriptor, argument_1, ..)
where 
argument_descriptor = a string describing the format and order of the arguments. Currently the following arguments can be passed:
'd' - file descriptor, expects an integer
'n' - file name, expects a string
'p' - file path, expects a string
'f' - flags, expects an integer
'c' - count, expects size_t
'b' - buf, expects char __user*

The output is displayed in the console, and can be redirected to any file by dmesg > echo > filename.txt.

Sys_open_logger is a draft - to be used to synch the print of the 'open' syscall logs in a file



################################################################################################################################
tutorial link

https://gabrio-tognozzi.medium.com/run-android-emulator-with-a-custom-kernel-547287ef708c
/* Stuff to fix: the makefile in goldfish in order for it to be compatible */


################################################################################################################################


