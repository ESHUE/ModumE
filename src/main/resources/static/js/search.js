function showSearchList(res) {
    console.log(res)
    // console.log(res.data.items)
    // console.log(res.data.items.length)
    const centralSearch = document.querySelector('.centralSearch');
    const searchResultContainer = document.querySelector('.searchResultContainer');
    if(searchResultContainer != null) {
       searchResultContainer.remove();
    }
 
    const makeDiv1 = document.createElement('div');
    makeDiv1.className = 'searchResultContainer';
    makeDiv1.style.position = 'absolute';
    makeDiv1.style.left = "15%";
    makeDiv1.style.width = '70%';
    makeDiv1.style.height = '22%';
    makeDiv1.style.zIndex = '3';
    
    const makeDiv11 = document.createElement('div');
    makeDiv11.className = 'swiper-container';
    makeDiv11.style.width = '100%';
    makeDiv11.style.height = '100%';

    const makeDiv111 = document.createElement('div');
    makeDiv111.className = 'swiper-wrapper';

    const makeDiv112 = document.createElement('div');
    makeDiv112.className = 'swiper-pagination';

    makeDiv11.append(makeDiv111);
    makeDiv11.append(makeDiv112);
    makeDiv1.append(makeDiv11);
    centralSearch.append(makeDiv1);

    for(let i=0; i<res.data.items.length; i++) {
      // console.log(res.data.items[i].snippet.thumbnails.medium);
      const makeDiv1111 = document.createElement('div');
      makeDiv1111.className = 'swiper-slide';
      const makeImg1 = document.createElement('img');
      makeImg1.setAttribute('src', res.data.items[i].snippet.thumbnails.medium.url);

      makeDiv1111.append(makeImg1);
      makeDiv111.append(makeDiv1111);
    }
}