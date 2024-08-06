document.addEventListener("DOMContentLoaded", function(){
	const statusClasses = {
		'SUBMITTED': 'status-submitted',
		'MODIFY': 'status-modify',
		'APPROVED': 'status-approved'
	};
	
	const completedStatusClasses = {
		'COMPLETED': 'status-completed',
		'INCOMPLETE': 'status-incomplete'
	};
	
	// Handle service status labels
	const statusElements = document.querySelectorAll('.item-info.service-status label');
	statusElements.forEach(label => {
		const statusText = label.textContent.trim().toUpperCase();
		if (statusClasses[statusText]){
			label.classList.add('status-pill', statusClasses[statusText]);
		}
	});
	
	// Handle service completed status labels
	const completedStatusElements = document.querySelectorAll('.item-info.service-completed-status label');
	completedStatusElements.forEach(label => {
		const statusText = label.textContent.trim().toUpperCase();
		if (completedStatusClasses[statusText]){
			label.classList.add('status-pill', completedStatusClasses[statusText]);
		}
	});
});
