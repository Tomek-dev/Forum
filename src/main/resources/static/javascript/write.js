function chooseOne(checkbox){
    let checkboxes = document.getElementsByName('type');
    checkboxes.forEach((item) => {
        if(item != checkbox){
            item.checked = false;
        }
    });
}