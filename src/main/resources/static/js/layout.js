window.addEventListener('DOMContentLoaded', event => {

  // Toggle the side navigation
  const snb = document.body.querySelector('.snb');
  const snbToggle = document.body.querySelector('#snbToggle');
  if (snbToggle && snb) {
    if (localStorage.getItem('sb|compact') === 'true') {
      snb.classList.toggle('compact');
    }
    snbToggle.addEventListener('click', event => {
      event.preventDefault();
      snb.classList.toggle('compact');
      localStorage.setItem('sb|compact', snb.classList.contains('compact'));
    });
  }

  const userSetting = document.body.querySelector('.snb .userSetting');
  const userArea = document.body.querySelector('.snb .userArea');
  if (userArea && userSetting) {
    userArea.addEventListener('click', event => {
      event.preventDefault();
      userSetting.classList.toggle('hidden');
    });
  }

  const layerCloseBtn = document.body.querySelector('.snb .userSetting .layerCloseBtn');
  if (layerCloseBtn && userSetting) {
    layerCloseBtn.addEventListener('click', event => {
      event.preventDefault();
      userSetting.classList.toggle('hidden');
    });
  }

});
