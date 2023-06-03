import { getMessaging, onBackgroundMessage } from "firebase/messaging/sw";
import { initializeApp } from "firebase/app";

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
const firebaseApp = initializeApp({
    apiKey: "AIzaSyCWPyQ_47Nqn7hsLsNzlR21C6lbB7yr3RQ",
    authDomain: "price-comparison-vue.firebaseapp.com",
    projectId: "price-comparison-vue",
    storageBucket: "price-comparison-vue.appspot.com",
    messagingSenderId: "431621825092",
    appId: "1:431621825092:web:336da9fb3f431fe0a61211",
    measurementId: "G-B9GLLY4ZV6"
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = getMessaging(firebaseApp);
onBackgroundMessage(messaging, (payload) => {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here
    const notificationTitle = payload.notification.title;
    const notificationOptions = {
        body: payload.notification.body,
        icon: '/bell.png'
    };

    self.registration.showNotification(notificationTitle,
        notificationOptions);
});
