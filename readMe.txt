WIP - custom kernel for android emulator

-> purpose is to be able to log syscalls directly from kernel in order to detect suspicious behaviour

#############################################################################################################
Build the kernel

make ARCH=x86_64 x86_64_ranchu_defconfig
make -j16 ARCH=x86_64 CROSS_COMPILE=x86_64-linux-android-


#############################################################################################################
Start emulator with custom kernel

Go under Android/Sdk/emulator folder and launch the emulator as follows:
./emulator -verbose @AVD_NAME -kernel /path/to/repo/goldfish/arch/x86/boot/bzImage -show-kernel -qemu — enable-kvm

#############################################################################################################

Run Test app provided in order to test the accuracy of the logs

The TestApp is a simple app that is designed to perform basic fs operations (open, read, write, close). The kernel logs should catch the app's syscalls.
Install the app in the emulator and run it by the following commands:
adb -s emulator-5554 install path_to_apk
adb -s emulator-5554 shell am start -n com.example.testapp/.MainActivity

#############################################################################################################


