window.onload = () => {
    ComponentEvent.getInstance().addClickEventImgAddButton();
    ComponentEvent.getInstance().addChangeEventImgFile();
    ComponentEvent.getInstance().addClickEventRegisterBtn();
}

const fileObj = {
    files : new Array()
}

class ImgfileService{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null){
            this.#instance = new ImgfileService();
        }
        return this.#instance;
    }

    getImgPreview(){
        const bookImg = document.querySelector(".book-img");

        const reader = new FileReader();

        reader.readAsDataURL(fileObj.files[0]);


        reader.onload = (e) => {
            bookImg.src = e.target.result;
        }

    }

}

class ComponentEvent{
    static #instance = null;
    static getInstance (){
        if(this.#instance == null){
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    } 

    addClickEventRegisterBtn(){
        const registerButton = document.querySelector(".register-button");
        
        registerButton.onclick = () => {
            if(confirm("도서 이미지를 등록하시겠습니까?")){
                const imgAddButton = document.querySelector(".img-add-button");
                const imgRegisterButton = document.querySelector(".img-register-button");
    
                imgAddButton.disabled = false;
                imgRegisterButton.disabled = false;
            }else{
                location.reload();
            }
        }


    }
    addClickEventImgAddButton(){
        const imgFile = document.querySelector(".img-file");
        const addButton = document.querySelector(".img-add-button");

        addButton.onclick = () => {
            imgFile.click();
        }
    }
    
    addChangeEventImgFile(){
        const imgFile = document.querySelector(".img-file");
        imgFile.onchange = () => {
            const formData = new FormData(document.querySelector(".img-form"));
            let changeFlag = false;

            fileObj.files.pop();

            formData.forEach(value => {
                console.log(value);

                if(value.size != 0){
                    fileObj.files.push(value);
                    changeFlag = true;
                }
            });

            if(changeFlag){
                ImgfileService.getInstance().getImgPreview();
                imgFile.value =  null;
            }

        }
    }
}