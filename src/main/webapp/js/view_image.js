const inpFile = document.getElementById("inpFile");
const previewContainer = document.getElementById("imagePreview");
const previewImage = previewContainer.querySelector(".image-preview__image");
const previewDefaultText = previewContainer.querySelector(".image-preview__default__text");

inpFile.addEventListener("change", function () {
    const file = this.files[0];

    if (file) {
        const reader = new FileReader();

        previewDefaultText.style.display = "none";
        previewImage.style.display = "block";

        reader.addEventListener("load", function () {
            previewImage.setAttribute("src", this.result);
        });

        reader.readAsDataURL(file);
    }
});

$("#fileupload").change(function(evt) {
    var fileUpload = $(this).get(0).files;
    readURL(this, "#trailer");
});

function readURL(inputFile, imgId) {
    if (inputFile.files && inputFile.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $(imgId).attr('src', e.target.result);
        }
        reader.readAsDataURL(inputFile.files[0]);
    }
}