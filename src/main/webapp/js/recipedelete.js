document.addEventListener("DOMContentLoaded", function () {
    const deleteLinks = document.querySelectorAll("#deleteLink")

    deleteLinks.forEach(function (link) {
        link.addEventListener("click", function (ev) {
            ev.preventDefault()
            let dataset = link.dataset
            let recipeId = dataset.recipeid
            let confirmation = confirm(`Delete recipe id: ${recipeId}?`)

            if (confirmation) {
                deleteRecipe(recipeId)
            }
        })
    })

    function deleteRecipe(id) {
        console.log(id)
        fetch("/app/recipe/delete?id=" + id,
            {method: "DELETE"})
            .then(function () {
                window.location.reload()
            })
    }
})