function showSearchList(res) {
    console.log(res)
    // console.log(res.data.items)
    // console.log(res.data.items.length)
    const centralSearch = document.querySelector('.centralSearch');
    closeSearch();
 
    // 맨 위 컨테이너
    const makeDiv1 = document.createElement('div');
    makeDiv1.className = 'searchResultContainer';
    makeDiv1.style.position = 'absolute';
    makeDiv1.style.left = "-80%";
    makeDiv1.style.width = '1280px';
    makeDiv1.style.zIndex = '3';
    
    // 스와이퍼 컨테이너
    const makeDiv11 = document.createElement('div');
    makeDiv11.className = 'swiper-container';
    makeDiv11.style.width = '100%';
    makeDiv11.style.height = '100%';

    // 스와이퍼 아이템들 감싸는 래퍼
    const makeDiv111 = document.createElement('div');
    makeDiv111.className = 'swiper-wrapper';

    // 스와이퍼 밑에 페이지 표시해주는 아이콘
    const makeDiv112 = document.createElement('div');
    makeDiv112.className = 'swiper-pagination';

    makeDiv11.append(makeDiv111);
    makeDiv11.append(makeDiv112);
    makeDiv1.append(makeDiv11);
    centralSearch.append(makeDiv1);

    // 스와이퍼 안에 들어갈 아이템들
    for(let i=0; i<res.data.items.length; i++) {
      // console.log(res.data.items[i].snippet.thumbnails.medium);
      const makeDiv1111 = document.createElement('div');
      makeDiv1111.className = 'swiper-slide';
      makeDiv1111.style.position = 'relative';
      makeDiv1111.style.overflow = 'hidden';
      
      const makeImg1 = document.createElement('img');
      makeImg1.setAttribute('src', res.data.items[i].snippet.thumbnails.medium.url);
      
      const makeDiv11111 = document.createElement('div');
      const makeDiv11111ClassName = 'playVideo' + i + ' cursor';
      makeDiv11111.className = makeDiv11111ClassName;
      makeDiv11111.style.position = 'absolute';
      makeDiv11111.style.left = '0';
      makeDiv11111.style.top = '0';
      makeDiv11111.style.width = '320px';
      makeDiv11111.style.height = '180px';
      makeDiv11111.style.backgroundColor = 'rgba(0, 0, 0, 0.8)';
      makeDiv11111.style.display = 'flex';
      makeDiv11111.style.alignItems = 'center';
      makeDiv11111.style.justifyContent = 'center';
      const makeSpan1 = document.createElement('span');
      const title = res.data.items[i].snippet.title;
      makeSpan1.innerText = title;
      makeSpan1.style.color = 'seashell';
      makeDiv11111.append(makeSpan1);
      
      makeDiv1111.append(makeImg1);
      makeDiv111.append(makeDiv1111);
      makeImg1.addEventListener("mouseenter", ()=>{
        makeDiv1111.append(makeDiv11111);
      });
      makeDiv11111.addEventListener("mouseleave", ()=>{
        makeDiv11111.remove();
      });
      makeDiv11111.addEventListener("click", ()=>{
        const youtubeUrl = 'https://www.youtube.com/watch?v=' + res.data.items[i].id.videoId;
        // console.log(youtubeUrl);
        closeSearch();
        openYoutube(youtubeUrl);
      });
    }
}

function closeSearch() {
  const searchResultContainer = document.querySelector('.searchResultContainer');
    if(searchResultContainer != null) {
       searchResultContainer.remove();
    }
}

function openYoutube(url) {
  const video_embed = document.querySelector('.video_embed');
  const twitchSwiper = document.querySelector('.slideContainer>.swiper-container');
  if(video_embed != null) {
    video_embed.remove();
  }
  if(twitchSwiper != null) {
    twitchSwiper.remove();
  }
  const makeDiv1 = document.createElement('div');
  makeDiv1.className = 'video_embed';
  makeDiv1.style.width = '100%';
  makeDiv1.style.height = '100%';

  loadReactPlayer(makeDiv1, url);
}

function loadReactPlayer(tag, url) {
  renderReactPlayer(tag, {
    url,
    playing: true,
    controls: true,
    width: '100%',
    height: '100%'
  });
  document.querySelector('.slideContainer').append(tag);
}

document.addEventListener("click",closeSearch);