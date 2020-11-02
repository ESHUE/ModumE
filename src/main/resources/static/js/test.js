
const slideContainer = document.querySelector('.slideContainer');

function newWrapper(){
	const Wrapper = document.createElement('div');
	Wrapper.classList.add('swiper-wrapper');
	return Wrapper;
}

function newSlider(){
	const swiperSlide = document.createElement('div');
	swiperSlide.classList.add('swiper-slide');
	return swiperSlide;
}

function newVertical(){
	const verticalSwiper = document.createElement('div');
	verticalSwiper.classList.add('swiper-container');
	verticalSwiper.classList.add('swiper-container-v');
	return verticalSwiper;
}

const horizenSwiper = document.createElement('div');
horizenSwiper.classList.add('swiper-container-h');
horizenSwiper.classList.add('swiper-container');

const swiperPagination = document.createElement('div');
swiperPagination.classList.add('swiper-pagination');
swiperPagination.classList.add('swiper-pagination-h');

horizenSwiper.appendChild(swiperPagination);
slideContainer.appendChild(horizenSwiper);
const hwrap = newWrapper();

function loadTwitchFollowSwiper(item){
	const hslide1 = newSlider();
	//const vertical1 = newVertical();
	//const vWrap = newWrapper();
	//const vslide1 = newSlider();
	
	horizenSwiper.appendChild(hwrap);
	hwrap.appendChild(hslide1);
	//hslide1.appendChild(vertical1);
	//vertical1.appendChild(vWrap);
	//vWrap.appendChild(vslide1);
	
	// horizen->wrap->silde->vertical->wrap->silde

	const name = document.createElement('div');
	const title = document.createElement('div');
	const thumbnail_url = document.createElement('div');
	//console.log(item);
	name.innerText = item.user_name;
	title.innerText = item.title;
	//thumbnail_url.innerText = item.thumbnail_url;
	item.thumbnail_url.replace('{width}','1920');
	item.thumbnail_url.replace('{height}','1080');
	
	thumbnail_url.setAttribute('src',item.thumbnail_url);
	hslide1.appendChild(name);
	hslide1.appendChild(title);
	hslide1.appendChild(thumbnail_url);
}

function reloadPagination(){
	horizenSwiper.removeChild(swiperPagination);
	horizenSwiper.appendChild(swiperPagination);

	var swiperH = new Swiper('.swiper-container-h', {
		spaceBetween: 50,
		slidesPerView: 1,
		mousewheel: true,
		autoHeight: false,
		pagination: {
			el: '.swiper-pagination-h',
			clickable: true
		},
	});
	var swiperV = new Swiper('.swiper-container-v', {
		direction: 'vertical',
		spaceBetween: 50,
		slidesPerView: 1,
		mousewheel: false,
		autoHeight: true,
		pagination: {
			el: '.swiper-pagination-v',
			clickable: true
		},
	});
}