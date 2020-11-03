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
function videoLoad() {
  const videoLink = document.querySelector(".swiper-slide-active>.thumbnail");
  const thumbnails = document.getElementsByClassName("thumbnail");
  for (var i = 0; i < thumbnails.length; i++) {
    thumbnails[i].id = "";
  }
  videoLink.id = "video-embed";

  const url = videoLink.getAttribute("streamerURL");
  const videoURL = "https://www.twitch.tv/" + url.substring(url.indexOf("user_") + 5, url.lastIndexOf("-1920x"));
  console.log(videoURL);
  const container = document.getElementById('video-embed')
  console.log(container)
  renderReactPlayer(container, {
    videoURL,
    playing: true,
    controls: false,
    width: "100%",
    height: "100%"
  })
}