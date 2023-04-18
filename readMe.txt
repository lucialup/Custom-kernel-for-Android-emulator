################################################################
tutorial link

https://gabrio-tognozzi.medium.com/run-android-emulator-with-a-custom-kernel-547287ef708c
/* Stuff to fix: the makefile in goldfish in order for it to be compatible */


################################################################
Build the kernel

make ARCH=x86_64 x86_64_ranchu_defconfig
make -j16 ARCH=x86_64 CROSS_COMPILE=x86_64-linux-android-


################################################################
Start emulator with custom kernel

Go under Android/Sdk/emulator folder and launch the emulator as follows:
./emulator -verbose @AVD_NAME -kernel /path/to/repo/goldfish/arch/x86/boot/bzImage -show-kernel -qemu — enable-kvm


/* e.g.

./emulator -verbose @tutorial1 -kernel /home/luci/Desktop/thesis/tutorials/tutorial1/goldfish/arch/x86/boot/bzImage -show-kernel -qemu -enable-kvm -read-only

*/
