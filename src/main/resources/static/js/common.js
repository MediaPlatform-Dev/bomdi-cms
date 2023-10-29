function fetchAndRender(apiPath, render) {
  fetch(apiPath)
    .then(response => response.json())
    .then(data => render(data))
    .catch(error => {
      console.error('error: ', error);
    });
}

function fetchPageAndRender(apiPath, render) {
    fetch(apiPath)
        .then(response => response.json())
        .then(data => render(data.content))
        .catch(error => {
            console.error('error: ', error);
        });
}
