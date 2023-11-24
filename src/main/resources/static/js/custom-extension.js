const customAddButton = $("#custom-add-button");
const customInput = $("#custom-input");

customInput.on("input", () => {
    if (customInput.val().length === 20) {
        alert("최대 20글자까지 입력 가능합니다.");
    }
});
const reg = /^[a-z]+$/;

customInput.on("input", (e) => {
    const inputValue = customInput.val();

    // 띄어쓰기(스페이스바) 입력 방지
    if (inputValue.includes(" ")) {
        alert("공백 없이 입력해주세요.");
        customInput.val(inputValue.replace(/\s/g, '')); // 띄어쓰기를 제거
        return;
    }

    // 영어 소문자로만 입력
    if (!reg.test(inputValue)) {
        alert("영어 소문자로만 입력해주세요.");
        customInput.val(inputValue.replace(/[^a-z]/g, '')); // 소문자 이외의 문자 제거
    }
});

customAddButton.on("click", () => {

    const customExtension = customInput.val();

    if (customExtension === "") {
        // 빈 문자열 입력 방지
        alert("확장자를 입력한 후 버튼을 눌러주세요.");
        return;
    }

    // Call the separate function for the AJAX request
    sendAjaxRequest(customExtension)

});

function sendAjaxRequest(customExtension) {
    $.ajax({
        url: "/api/custom-extensions/toggle-check",
        method: "POST",
        data: { ceName: customExtension },
        success: function(response) {

            getCount(); //count(*)

            addExtensionToDisplay(customExtension); // div append

            alert(response); // Display the success message

            $("#custom-input").val("");

        },
        error: function(jqXHR) {
            if (jqXHR.status === 400) {
                alert(jqXHR.responseText); // Display the specific error message
            } else {
                console.error("AJAX Error:", jqXHR.statusText);
                alert("오류가 발생했습니다.");
            }
        }
    });
}

function getCount() {
    // 두 번째 Ajax 호출: count(*) 조회
    $.ajax({
        url: "/api/custom-extensions/count",
        method: "GET",
        success: function(response) {
            // 받아온 count 값을 <p id="customCount"> 엘리먼트에 업데이트
            $("#customCount").text(response + " / 200");
        },
        error: function(jqXHR) {
            alert("count 조회 중에 오류가 발생했습니다.");
        }
    });
}

function addExtensionToDisplay(customExtension) {
    // 동적으로 화면에 확장자를 추가

    const extensionsList = $("#customExtensionsList");
    const extensionElement = $("<div class='extension-item'></div>")
        .text(customExtension)
        .append("<span class='delete-button'>X</span>")
        .appendTo(extensionsList);

    // 삭제 버튼에 클릭 이벤트 추가
    extensionElement.find(".delete-button").on("click", function() {
        deleteExtension($(this).parent());
    });
}

function deleteExtension(extensionElement) {
    // 삭제 버튼을 클릭하면 확장자를 삭제하고 서버에도 삭제 요청을 보냄


    if(confirm("정말 삭제하시겠습니까?")){

        const customExtension = extensionElement.text().trim();
        extensionElement.remove();

        $.ajax({
            url: "/api/custom-extensions/delete",
            method: "DELETE",
            data: { ceName: customExtension.replace('X', '') },  // "X"를 제거
            success: function(response) {
                getCount(); // 삭제 후 count(*) 다시 조회
                alert(response);
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });

    }
}