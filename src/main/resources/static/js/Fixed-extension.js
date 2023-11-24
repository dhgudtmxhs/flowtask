

$(document).ready(() => {
    // Set initial checkbox states based on local storage
    setInitialCheckboxStates();

    // Add event listener for checkbox clicks
    $('input[name="fixed-extension"]').on('change', function() {
        const checkboxId = $(this).attr("id");
        const isChecked = $(this).prop('checked');

        // Update database and local storage
        updateCheckBox(checkboxId, isChecked);
    });
});

function setInitialCheckboxStates() {
    // Iterate through checkboxes and set their state based on local storage
    $('input[name="fixed-extension"]').each(function() {
        const checkboxId = $(this).attr("id");
        const isChecked = localStorage.getItem(checkboxId) === 'true';
        $(this).prop('checked', isChecked);
    });
}

function updateCheckBox(checkboxId, isChecked) {
    $.ajax({
        type: "POST",
        url: "/api/fixed-extensions/toggle-check",
        contentType: "application/json",
        data: JSON.stringify({
            feName: checkboxId,
            isChecked: isChecked
        }),
        success: function (response) {
            console.log("Server response:", response);

            // Assuming the server returns the updated state in the response
            const updatedState = response.isChecked;

            // Update local storage with the new state
            localStorage.setItem(checkboxId, updatedState.toString());
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
            alert("Checkbox update error");
        }
    });

}

function validateFile(fileInput) {

    const file = fileInput.files[0];

   // const FileReg = /a/

    if (file && /\.(jpg|bmp|jpeg|png|exe)$/.test(file.name)) {

        // 정규식 문제 없으면 삽입

    } else {

        // 안되면 삽입 x
        fileInput.value = "";

        alert("차단하신 확장자는 삽입이 불가능합니다.");
    }
}