let numClicks = 0;

window.addEventListener("DOMContentLoaded", () => {
	const button = document.getElementById("create-person-btn");
	button.addEventListener("click", () => {
		const messageElement = document.getElementById("create-person-err-msg");
		numClicks++;
		messageElement.innerHTML = `<b>Clicked ${numClicks} time(s)</b>`;
	});
});
