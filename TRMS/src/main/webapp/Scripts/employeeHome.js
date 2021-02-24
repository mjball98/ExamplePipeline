/**
 * 
 */

let pendingButton = documenet.getElementById("pending")
pendingButton.addEventListener('click', viewPending)

let resolvedButton = document.getElementById("resolved")
resolvedButton.addEventListener('click', viewResolved)

let viewButton = document.getElementById("view")
viewButton.addEventListener('click', viewInfo)

function tempFunc(event){
	
	if(event.cancelable){
         event.preventDefault()
        }

}

function viewInfo(event){
	
	if(event.cancelable){
         event.preventDefault()
        }
	
}

function viewPending(event){
	if(event.cancelable){
         event.preventDefault()
        }
}

function viewResolved(event){
	if(event.cancelable){
         event.preventDefault()
        }
}

