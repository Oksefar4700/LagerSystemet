<script>
    const video = document.getElementById('video');
    const canvas = document.getElementById('canvas');
    const snap = document.getElementById('snap');
    const context = canvas.getContext('2d');

    navigator.mediaDevices.getUserMedia({ video: true })
    .then((stream) => {
        video.srcObject = stream;
    });

    snap.addEventListener("click", () => {
        context.drawImage(video, 0, 0, 640, 480);
        let picture = canvas.toDataURL(); // convert image to data URL

        // Create a hidden form element to send the image data to the server
        const hiddenForm = document.createElement('form');
        hiddenForm.setAttribute('method', 'POST');
        hiddenForm.setAttribute('action', '/save-picture'); // Update the action URL according to your server endpoint

        const hiddenInput = document.createElement('input');
        hiddenInput.setAttribute('type', 'hidden');
        hiddenInput.setAttribute('name', 'picture');
        hiddenInput.setAttribute('value', picture);

        hiddenForm.appendChild(hiddenInput);
        document.body.appendChild(hiddenForm);

        // Submit the form to save the picture on the server
        hiddenForm.submit();
    });
</script>