let originalColors = true

function changeColors(){
    //First we need to select the web elements that we wish to manipulate
    let h1 = document.getElementsByTagName('h1')
    let h2 = document.getElementsByTagName('h2')

    //Now let's manipulate the elements we'd like to change. In our case, we wish to change the color of our h1 and h2 text.

    if(this.originalColors){
        h1[0].style.color = 'orange'
        h2[0].style.color= 'gray'
    }else{
        h1[0].style.color = 'gray'
        h2[0].style.color = 'orange'
    }

    //Change the originalColors state to reflect that this function has been called and the text color has been updated
    this.originalColors = !this.originalColors
}

// Currently, this function doesn't listen for any events. Thus, we can't use it to dynamically modify the colors our text. In order to do so, we'll have to use what we refer to as an "event listener". Event listeners "listen" for certain events on certain elements. Note that we add event listeners by associating JavaScript Events (Event is actually a JS object) with a callback function. A callback function is a function that is passed to another function as an argument and later invoked within that function.

//Let's select the element we would like to add an event listener to. We will add our listener to the containing div for the h1 and h2 (e.g. the div with the id "message").

let messageDiv = document.getElementById('message')

//Now let's add an event listener to this element.

messageDiv.addEventListener('click', changeColors)

//We can also (and should) use JavaScript for tasks that are not purely cosmetic. We can also use it to perform client-side validation. Note that client-side validation is NOT enough to validate user information, so it should never be used alone; do NOT skip server-side validation. That said, let's try our hand at writing a function which validates the length of a user password.

//The first time we wrote this function, we did not account for two things:
//1. The message continues to be added infinitely to the form. We would like to restrict that message so that it only appears once.
//2. The form can still be submitted despite our check on the password's length.

let hasNoMessage = true

function validatePassword(event){
    //We need to grab the element whose value we're trying validate

    let inputBoxes = document.getElementsByTagName('input')
    //To isolate the user input in this input box, we can use the "value" property
    let userPass = inputBoxes[1].value

    if(userPass.length < 8){
        //We should tell the user WHY the form is not being submitted.

        //Let's first select the form on our web page.

        if(hasNoMessage){
        let form = document.getElementById('form')

        //Let's create the error message we wish to append to it

        let errorMessage = document.createElement('p')

        //Specify the text for the new paragraph as it is currently empty
        errorMessage.innerText = 'Password must be at least 8 characters long!'

        //Now that we're specified the text content of our paragraph, let's append it to the form!

        form.append(errorMessage)

        hasNoMessage = false
        }

        //Ideally, the form would not be submitted if the password's length was not sufficient. As such, we want to prevent the default behavior (submitting the form) of clicking the button.

        //Be sure to check than an event is cancelable before calling preventDefault.

        if(event.cancelable){
         event.preventDefault()
        }
    }
}

// Let's create an event listener for the form's button

let button = document.querySelector('button')
button.addEventListener('click', validatePassword)

/*
Whenever we add event listeners to elements, there is actually an order in which the events are fired off. This becomes more apparent when we have multiple event listeners associated with the same element.
*/

messageDiv.addEventListener('click', () => {
    //Let's make one of those spammy alert boxes pop up in the browswer when this element is clicked

    window.alert("You've won a free Iphone because you clicked this div")
}, true)

let innerH1 = document.querySelector('h1')

innerH1.addEventListener('click', () => {
    window.alert("You've won one billion dollars because you clicked this h1")
})

//The default order in which events are propagated in JS is from the innermost element to the outermost element. This is referred to as "bubbling" (and it visually resembles blowing a bubble).

//You can change the default order in which events are propagated and use "capturing" instead. Capturing allows us to make our outermost events occur first and innermost events occur last.
