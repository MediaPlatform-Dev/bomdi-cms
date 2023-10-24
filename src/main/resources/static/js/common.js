function fetchAndRender(apiPath, render) {
  fetch(apiPath)
    .then(response => response.json())
    .then(data => render(data))
    .catch(error => {
      console.error('error: ', error);
    });
}