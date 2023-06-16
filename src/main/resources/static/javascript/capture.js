// Reference to the video and canvas elements
const video = document.getElementById('video');
const canvas = document.getElementById('capturedImage');
const context = canvas.getContext('2d');

// Set the desired width and height for the captured image
const captureWidth = 320;
const captureHeight = 240;

// Reference to the Snap Photo button
const snap = document.getElementById('snap');

// Get access to the webcam
navigator.mediaDevices.getUserMedia({ video: true })
  .then((stream) => {
    video.srcObject = stream;
  });

// Add click listener to the Snap Photo button
snap.addEventListener("click", () => {
  // Set the canvas dimensions to the desired capture size
  canvas.width = captureWidth;
  canvas.height = captureHeight;

  // Draw the current frame of the video on the canvas
  context.drawImage(video, 0, 0, captureWidth, captureHeight);

  // Convert the canvas image to a Blob object
  canvas.toBlob((blob) => {
    if (!blob) {
      console.log('No image blob created');
      return;
    }
    // Create a FormData object to send the image file to the server
    const formData = new FormData();
    formData.append('picture', blob, 'image.png');

    // Send the captured image to the server
    fetch('/upload-picture', {
      method: 'POST',
      body: formData
    })
    .then((response) => response.text())
    .then((data) => {
      console.log('Image uploaded:', data);
      // Set the hidden input field value to the returned image URL or identifier
      document.getElementById('hiddenPictureUrl').value = data;
    })
    .catch((error) => {
      console.error('Error uploading image:', error);
    });
  }, 'image/png');
});
