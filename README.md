Automatic Lighting Control System

This project is an Android application built using Kotlin in Android Studio, designed to control an automatic lighting system. The application communicates with a Firebase Realtime Database to send control signals to an ESP32 module, which in turn manages the switching of lights on and off.
Features

    User Interface: Simple and intuitive UI for controlling lights, displaying current status, and easy navigation.
    Firebase Integration: Real-time communication with Firebase to store and retrieve lighting control data.
    ESP32 Communication: Sends control signals to an ESP32 module to manage the physical on/off states of the lights.
    Authentication: Firebase Authentication integrated for secure access.

Setup and Installation

    Clone the repository:

    bash

    git clone https://github.com/mochamadbahrulngulum/Control_Lampu_Otomatis.git
    cd Control_Lampu_Otomatis

    Open in Android Studio:
        Open Android Studio and select "Open an existing Android Studio project."
        Navigate to the cloned repository and open the project.

    Firebase Configuration:
        Add your Firebase project's google-services.json file to the app directory.
        Ensure Firebase Authentication, Realtime Database, and any other required services are enabled.

    ESP32 Setup:
        Ensure your ESP32 module is properly configured to communicate with Firebase.
        Upload the appropriate code to the ESP32 module that listens for data from Firebase and controls the lights.

    Build and Run:
        Build the project and run it on your Android device.
        Authenticate and start controlling your lights!

Usage

    Controlling Lights:
        Use the app to send on/off commands to the lights.
        The app will update the Firebase Realtime Database, which in turn sends the command to the ESP32 module.

    Monitoring Status:
        The current status of the lights (on/off) is displayed in real-time within the app, reflecting the state stored in Firebase.

Technologies Used

    Kotlin
    Android Studio
    Firebase Realtime Database
    Firebase Authentication
    ESP32

Contributing

Feel free to fork this repository, submit issues, and send pull requests. Contributions are welcome!
License

This project is licensed under the MIT License - see the LICENSE file for details.
