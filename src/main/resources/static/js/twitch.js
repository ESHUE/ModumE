/**
 * 
 */
axios.get('/getStreams', {}).then(function (res) {
							res.data.data.forEach(function (item) {
								loadTwitchFollowSwiper(item)
							})
							reloadPagination();
						}).then(function () {
							const playBtn = document.querySelectorAll(".playBtn");
							for (var i = 0; i < playBtn.length; i++) {
								playBtn[i].setAttribute("onclick", "videoLoad()");
							}
						})
						
						function videoLoad() {
							const videoLink = document.querySelector(".swiper-slide-active>.thumbnail");
							const thumbnails = document.getElementsByClassName("thumbnail");
							for (var i = 0; i < thumbnails.length; i++) {
								thumbnails[i].id = "";
							}
							videoLink.id = "video-embed";
							const url = videoLink.getAttribute("streamerURL");
							const videoURL = "https://www.twitch.tv/" + url.substring(url.indexOf("user_") + 5, url.lastIndexOf("-1920x"));
							streamerID = url.substring(url.indexOf("user_") + 5, url.lastIndexOf("-1920x"));
							/* streamerID 따로 저장 */
							saveRoomId(streamerID);
							console.log(videoURL);
							reactPlayer(videoURL);
							axios.get("/autoJoin",{
								params:{
									streamerID
								}
							}).then(function(result){
								
							})
						}
						function reactPlayer(url) {
							const container = document.getElementById('video-embed')
							console.log(container)
							renderReactPlayer(container, {
								url,
								playing: true,
								controls: false,
								width: "100%",
								height: "100%"
							})
						}