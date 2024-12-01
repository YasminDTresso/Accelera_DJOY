/*======================VIEW======================*/


/*---------------+SIDEBAR+---------------*/
const sidebar = document.querySelector("#sidebar");
const hamburger = document.querySelector("#toggle-btn");
const icon_toggle_btn = hamburger.querySelector("i");
const dropdown_link = document.querySelector("#dropdown-link");
const multi_link = document.querySelector("#multi-link");


hamburger.addEventListener("click", function(){
    sidebar.classList.toggle("expand");

        // Verifica se a classe atual do ícone é "uil-angle-left-b"
        if (icon_toggle_btn.classList.contains("uil-angle-left-b")) {
            // Altera para a nova classe "uil-angle-right-b"
            icon_toggle_btn.classList.remove("uil-angle-left-b");
            icon_toggle_btn.classList.add("uil-angle-right-b");
        } else {
            // Caso contrário, volta para a classe "uil-angle-left-b"
            icon_toggle_btn.classList.remove("uil-angle-right-b");
            icon_toggle_btn.classList.add("uil-angle-left-b");
        }
    
})

dropdown_link.addEventListener("click", function(){
    if (!sidebar.classList.contains("expand")) {
        sidebar.classList.add("expand");  // Adiciona a classe "expand"
    }
})

multi_link.addEventListener("click", function(){
    if (!sidebar.classList.contains("expand")) {
        sidebar.classList.add("expand");  // Adiciona a classe "expand"
    }
})





