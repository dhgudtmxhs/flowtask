

$(document).ready(() => {

    setCheckboxStates();

    $('input[name="fixed-extension"]').on('change', function() {
        const checkboxId = $(this).attr("id");
        const isChecked = $(this).prop('checked');
        updateCheckBox(checkboxId, isChecked);
    });

});

// 체크박스 상태 가져오기
function setCheckboxStates() {

    $('input[name="fixed-extension"]').each(function() {
        const checkboxId = $(this).attr("id");
        const isChecked = localStorage.getItem(checkboxId) === 'true';
        $(this).prop('checked', isChecked);
    });

}

// 체크박스 상태 업데이트
function updateCheckBox(checkboxId, isChecked) {
    $.ajax({
        type: "POST",
        url: "/api/fixed-extensions/insert-fe-extension",
        contentType: "application/json",
        data: JSON.stringify({
            feName: checkboxId,
            isChecked: isChecked
        }),
        success: function (response) {

            const updatedState = response.isChecked;
            localStorage.setItem(checkboxId, updatedState.toString()); // 담아두기
        },
        error: function (error) {
            console.error(error);
        }
    });

}

