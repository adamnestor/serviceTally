document.addEventListener("DOMContentLoaded", function(){
	const statusClasses = {
		'SUBMITTED': 'status-submitted',
		'MODIFY': 'status-modify',
		'APPROVED': 'status-approved'
	};
	
	const statusElements = document.querySelectorAll('.item-info.service-status label');
	
	statusElements.forEach(label => {
		const statusText = label.textContent.trim().toUpperCase();
		if (statusClasses[statusText]){
			label.classList.add('status-pill', statusClasses[statusText]);
		}
	});
});