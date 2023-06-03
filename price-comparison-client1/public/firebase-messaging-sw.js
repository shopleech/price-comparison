// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here. Other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
firebase.initializeApp({
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
const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
    console.log(
        '[firebase-messaging-sw.js] Received background message ',
        payload
    );
    // Customize notification here
    const notificationTitle = payload.notification.title;
    const notificationOptions = {
        body: payload.notification.body,
        icon: '/bell.png'
    };

    self.registration.showNotification(notificationTitle, notificationOptions);
});
