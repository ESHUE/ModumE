const slideContainer = document.querySelector(".slideContainer");
const SwiperContainer = document.createElement("div");
SwiperContainer.classList.add("swiper-container");
slideContainer.appendChild(SwiperContainer);
SwiperContainer.style.width = "100%";
SwiperContainer.style.height = "100%";
SwiperContainer.style.marginLeft = "auto"
SwiperContainer.style.overflow = "hidden";
SwiperContainer.style.borderRadius = "5px";
const Wrapper = document.createElement("div");

function getWrapper() {
  Wrapper.classList.add("swiper-wrapper");
  SwiperContainer.appendChild(Wrapper);
  return Wrapper;
}

function newSlider() {
  const swiperSlide = document.createElement("div");
  swiperSlide.classList.add("swiper-slide");
  swiperSlide.style.textAlign = "center";
  swiperSlide.style.fontSize = " 18px";
  swiperSlide.style.background = "#fff";
  swiperSlide.style.display = "flex";
  swiperSlide.style.justifyContent = "center";
  swiperSlide.style.alignItems = "center";
  return swiperSlide;
}

function newScrollbar() {
  const swiperPagination = document.createElement("div");
  swiperPagination.classList.add("swiper-scrollbar");
  swiperPagination.style.color = " floralwhite";
  return swiperPagination;
}

function loadTwitchFollowSwiper(item) {
  const slide = newSlider();
  const name = document.createElement("span");
  const title = document.createElement("span");
  const thumbnail_url = document.createElement("div");
  //console.log(item);
  name.innerText = item.user_name;
  name.style.position = "absolute";
  name.style.margin = "1%";
  name.style.color = "floralwhite";
  name.style.fontSize = "2em";
  name.style.padding = "1%";
  name.style.background = "rgba(150,150,150,.5)";
  name.style.display = "inline-flex";
  name.style.borderRadius = "10px";
  name.style.left= "0";
  name.classList.add("streamerName");
  title.innerText = item.title;
  title.style.position = "absolute";
  title.style.margin = "1%";
  title.style.color = "floralwhite";
  title.style.fontSize = "1.5em";
  title.style.padding = "1%";
  title.style.background = "rgba(150,150,150,.5)";
  title.style.display = "inline-flex";
  title.style.borderRadius = "10px";
  title.style.top = "0";
  title.style.left = "0";
  title.classList.add("streamerTitle");
  //thumbnail_url.innerText = item.thumbnail_url;
  item.thumbnail_url = item.thumbnail_url.replace("{width}", "1920");
  item.thumbnail_url = item.thumbnail_url.replace("{height}", "1080");
  console.log(item.thumbnail_url);

  thumbnail_url.style.backgroundImage = `url(${item.thumbnail_url})`;
  thumbnail_url.classList.add("thumbnail");
  thumbnail_url.style.position = "relative";
  thumbnail_url.style.backgroundPosition = "center";
  thumbnail_url.style.backgroundSize = "cover";
  thumbnail_url.style.width = "100%";
  thumbnail_url.style.height = "100%";
  thumbnail_url.style.display = "flex";
  thumbnail_url.style.flexFlow = "column-reverse";
  thumbnail_url.setAttribute('streamerURL', item.thumbnail_url);
  thumbnail_url.appendChild(name);
  thumbnail_url.appendChild(title);

  const playbtn = document.createElement("span");
  playbtn.classList.add("material-icons");
  playbtn.classList.add("playBtn");
  playbtn.innerText = "play_circle_outline";
  thumbnail_url.appendChild(playbtn);

  slide.appendChild(thumbnail_url);
  getWrapper().appendChild(slide);
}

function reloadPagination() {
  SwiperContainer.appendChild(newScrollbar());
  var swiper = new Swiper(".slideContainer>.swiper-container", {
    direction: "vertical",
    slidesPerView: 1,
    spaceBetween: 30,
    mousewheel: true,
    scrollbar: {
      el: ".swiper-scrollbar",
      clickable: true,
      hide: true,
      draggable:true,
      clickable:true
    },
  });
}
