/**
 * 
 */

function grabReimbursements() {
	//We want to select the div which contains all of the cards and append more cards to this div
	let div = document.querySelector('.reimbursement-info')

	//I want to place cards inside of this div. BUT I need to get the cards first. I have already set up an endpoint which I can use to access information about monster cards, so I can use this endpoint to grab my monster cards. We'll need AJAX to accomplish this task.

	//Let's start by creating an XMLHttpRequest object:

	let xhr = new XMLHttpRequest() //readyState 0

	//We need to define what we want to do when the readyState is 4 (meaning that the response body has been populated on the server side); in our case, we want to put some cards on the page for the user to see. We can use the onreadystatechange event listener to listen for changes to our readyState. In essence, this listener is invoked each time the readyState changes.

	xhr.onreadystatechange = function() {
		//You decide what to do each time the readyState changes in this function! Be sure to check that the readyState is 4 and and that the response code is 200 (meaning that everything went smoothly)

		if (xhr.readyState === 4 & xhr.status === 200) {
			//JSON.parse is a convenience function for parsing JSON as a JavaScript object
			let r2 = (xhr.response)
			//need to take the zero off, then can parse as json
			let r3 = r2.slice(0,-1)
			console.log('the ready state was 4 and the status was 200')
			console.log(r3)
			
			let res = JSON.parse(r3)
			//console.log(typeof(r2))
			
			
			//let res = r2.split('},')
			
			
			
			console.log(res[0])
			console.log(res[0].amount)

			//We will add all our new cards as divs, so let's create a new div for each
			for (let r of res) {
				let newR = document.createElement('div')
				let id = document.createElement('p')
				let amount = document.createElement('p')
				let employee_id = document.createElement('p')
				let status = document.createElement('p')
				let reciept = document.createElement('p')
				let resolving_manager_id = document.createElement('p')

				id.innerText = ('Id: ' + r.id)
				amount.innerText = ('Amount: ' + r.amount)
				employee_id.innerText = ('Employee Id: ' + r.employee_id)
				status.innerText = ('Status: '+ r.status)
				reciept.innerText = ('Reciept: ' + r.reciept)
				resolving_manager_id.innerText = ('Resolving Manager Id: ' + r.resolving_manager_id)

				newR.append(id)
				newR.append(amount)
				newR.append(employee_id)
				newR.append(status)
				newR.append(reciept)
				newR.append(resolving_manager_id)

				div.append(newR)
			}

		}
	}

	//Open my XMLHttpRequest, specifying my HTTP verb and the endpoint I would like to hit.

	//xhr.open('GET', 'http://localhost:8080/TRMS/api/Reimbursement/ResolvedForManager') //readyState 1
	//http://18.219.228.37:8088
	xhr.open('GET', 'http://18.219.228.37:8088/TRMS/api/Reimbursement/ResolvedForManager') //readyState 1
	xhr.send() //readyState 2
}



window.onload = () => {
	console.log('window on load!')
	grabReimbursements()
}