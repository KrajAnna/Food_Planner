document.addEventListener("DOMContentLoaded", function () {


    const submitButton = document.querySelector(".btn")
    submitButton.addEventListener("click", function (ev) {
        ev.preventDefault()

        const planId = document.querySelector("#choosePlan").value
        const mealType = document.querySelector("#mealType").value
        const mealOrder = document.querySelector("#number").value
        const recipeId = document.querySelector("#recipie").value
        const dayId = document.querySelector("#day").value

        const url = "/app/recipe/plan/add" +
            `?planId=${planId}&mealType=${mealType}&mealOrder=${mealOrder}&recipeId=${recipeId}&dayId=${dayId}&`
        send(url)
    })

    function send(url) {
        fetch(url,
            {method: "POST"})
            .then(function (){
                window.location.reload()
            })
    }

})