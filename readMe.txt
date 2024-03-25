WIP - custom kernel for Android emulator

1. Requirements:
The goal of this project is to enable the detection of malicious behavior in the Android Operating System by syscall hooking directly into the Android Kernel. The system should capture and log the performed syscalls and integrate the YARA pattern-matching engine to allow real-time detection of potential malware.

2. Proposed solutions:
Our solution involves the deployment of a custom Android Goldfish kernel in an emulated environment. Within the Goldfish kernel, we integrated a standalone hook function to capture and log syscalls. The function is invoked at the start of each system call that we want to trace (e.g., ‘write’), intercepts its arguments (file descriptor, path, filename, buffer, and count), logs them, and then allows the system call to proceed.
Moreover, the YARA engine is integrated into the system, and custom YARA rules are created in order to identify potential ransomware behavior based on predefined patterns (e.g., the encryption of device photos). At last, a user-friendly interface is developed using NodeJs and NodeGtk, providing users with easy interaction and control over the Android emulator. Users can manage the emulator, load APKs, view syscall logs, and apply custom YARA rules for effective system monitoring and malware detection.

3. Results obtained:
After running the system, we generate detailed logs of monitored syscalls from the Android Goldfish Kernel, showcasing the implementation of our custom hook function integration. We then redirect the logs into a text file against which we run the custom YARA rules in order to spot ransomware-like patterns (e.g., a rapid succession of open, write, and close).

4. Tests and verifications:
To verify the correctness of the syscall hooking implementation, simple Android applications were developed to conduct various file operations. The synchronization and
locking mechanisms of the verbose hook function were also tested, proving its capability to manage deadlocks and data loss. The YARA engine integration was validated through a successful match of a ransomware-detecting YARA rule against syscall logs from a simulated ransomware application, showcasing the YARA custom rules’ effectiveness.

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


