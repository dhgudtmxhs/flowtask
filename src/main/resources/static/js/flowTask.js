const customAddButton = $("#custom-add-button");

const customInput = $("#custom-input");

customAddButton.on("click", () => {
    alert("test");
});

customInput.on("input", () => {
    if (customInput.val().length === 20) {
        alert("최대 20글자까지 입력 가능합니다.");
    }
});


$(document).ready(() => {
    $('input[name="fixed-extension"]').each(function() {
        const checkboxId = $(this).attr("id");
        const isChecked = localStorage.getItem(checkboxId) === 'true';

        $(this).prop('checked', isChecked);
    });

    // Add event listener for checkbox clicks
    $('input[name="fixed-extension"]').on('change', function() {
        const checkboxId = $(this).attr("id");
        const isChecked = $(this).prop('checked');

        // Update database and local storage
        updateCheckBox(checkboxId, isChecked);
    });
});

function updateCheckBox(extension, isChecked) {
    $.ajax({
        type: "POST",
        url: "/api/fixed-extensions/toggle-check?extensionName=" + extension,
        contentType: "application/json",
        data: JSON.stringify({ isChecked: isChecked }),  // send the boolean value directly
        success: function(response) {
            // Update local storage with the new state
            localStorage.setItem(extension, isChecked ? 'true' : 'false');
        },
        error: function(error) {
            alert("Failed to update checkbox state.");
        }
    });
}