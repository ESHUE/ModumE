const slideContainer = document.querySelector('.slideContainer');
var swiperH = new Swiper('.swiper-container-h', {
    spaceBetween: 50,
    pagination: {
      el: '.swiper-pagination-h',
      clickable: true,
    },
});
var swiperV = new Swiper('.swiper-container-v', {
    direction: 'vertical',
    spaceBetween: 50,
    pagination: {
      el: '.swiper-pagination-v',
      clickable: true,
    },
});
// swiper-container swiper-container-h 안에
// swiper-wrapper 안에
// swiper-slide 안에
// swiper-container swiper-container-v 안에
// swiper-wrapper 안에
// swiper-slide
function init() {
    const swiper1 = document.createElement('div');
    swiper1.className = 'swiper-container swiper-container-h';

    const swiper1_1 = document.createElement('div');
    swiper1_1.className = 'swiper-wrapper';

    swiper1.append(swiper1_1);
    slideContainer.append(swiper1);
}

init();