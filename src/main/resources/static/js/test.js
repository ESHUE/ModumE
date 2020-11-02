const slideContainer = document.querySelector(".slideContainer");
const SwiperContainer = document.createElement("div");
SwiperContainer.classList.add("swiper-container");
slideContainer.appendChild(SwiperContainer);

const Wrapper = document.createElement("div");
function getWrapper() {
  Wrapper.classList.add("swiper-wrapper");
  SwiperContainer.appendChild(Wrapper);
  return Wrapper;
}

function newSlider() {
  const swiperSlide = document.createElement("div");
  swiperSlide.classList.add("swiper-slide");
  return swiperSlide;
}

function newPagination() {
  const swiperPagination = document.createElement("div");
  swiperPagination.classList.add("swiper-pagination");
  return swiperPagination;
}

function loadTwitchFollowSwiper(item) {
  const slide = newSlider();
  const name = document.createElement("span");
  const title = document.createElement("span");
  const thumbnail_url = document.createElement("div");
  //console.log(item);
  name.innerText = item.user_name;
  name.classList.add("streamerName");
  title.innerText = item.title;
  title.classList.add("streamerTitle");
  //thumbnail_url.innerText = item.thumbnail_url;
  item.thumbnail_url = item.thumbnail_url.replace("{width}", "1920");
  item.thumbnail_url = item.thumbnail_url.replace("{height}", "1080");
  console.log(item.thumbnail_url);

  thumbnail_url.style.backgroundImage = `url(${item.thumbnail_url})`;
  thumbnail_url.classList.add("thumbnail");
  thumbnail_url.appendChild(name);
  thumbnail_url.appendChild(title);
  slide.appendChild(thumbnail_url);
  getWrapper().appendChild(slide);
}

function reloadPagination() {
  SwiperContainer.appendChild(newPagination());
  var swiper = new Swiper(".swiper-container", {
    direction: "vertical",
    slidesPerView: 1,
    spaceBetween: 30,
    mousewheel: true,
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
  });
}
