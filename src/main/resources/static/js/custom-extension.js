const customAddButton = $("#custom-add-button");
const customInput = $("#custom-input");

customInput.on("input", () => {
    if (customInput.val().length === 20) {
        alert("최대 20글자까지 입력 가능합니다.");
    }
});
const reg1 = /^[a-z]+$/;

customInput.on("input", (e) => {
    const inputValue = customInput.val();

    // 띄어쓰기(스페이스바) 입력 방지
    if (inputValue.includes(" ")) {
        alert("공백 없이 입력해주세요.");
        customInput.val(inputValue.replace(/\s/g, '')); // 띄어쓰기 제거
        return;
    }

    // 영어 소문자로만 입력
    if (!reg1.test(inputValue)) {
        alert("영어 소문자로만 입력해주세요.");
        customInput.val(inputValue.replace(/[^a-z]/g, '')); // 소문자 이외 문자 제거
    }
});

customAddButton.on("click", () => {

    const customExtension = customInput.val();

    if (customExtension === "") {
        alert("확장자를 입력한 후 버튼을 눌러주세요."); // 빈 문자열 입력 방지
        return;
    }

    insertCtExtension(customExtension);

});

// 커스텀 확장자 삽입
function insertCtExtension(customExtension) {
    $.ajax({
        url: "/api/custom-extensions/insert-ct-extension",
        method: "POST",
        data: { ceName: customExtension },
        success: function(response) {

            getCount(); //count(*)

            addExtensionToDisplay(customExtension); // div append로 추가

            alert(response); // 확장자 삽입 알림

            $("#custom-input").val(""); // input 비우기

        },
        error: function(error) {
            console.error(error);
        }
    });
}

//count(*) 조회
function getCount() {

    $.ajax({
        url: "/api/custom-extensions/count",
        method: "GET",
        success: function(response) {
            // <p id="customCount"> 업데이트
            $("#customCount").text(response + " / 200");
        },
        error: function(error) {
            console.error(error);
        }
    });
}

// 동적 커스텀 확장자 추가
function addExtensionToDisplay(customExtension) {


    const extensionsList = $("#customExtensionsList");
    const extensionElement = $("<div class='extension-item'></div>")
        .append("<span class='extension-text'></span>")
        .append("<span class='delete-button'>X</span>")
        .appendTo(extensionsList);

    extensionElement.find('.extension-text').text(customExtension);

    // 삭제 버튼에 클릭 이벤트 추가
    extensionElement.find(".delete-button").on("click", function() {
        deleteExtension($(this).parent());
    });
}



// 커스텀 확장자 삭제 (초기화면, 동적으로 추가한 확장자)
function deleteExtension(extensionElement) {
    if (confirm("정말 삭제하시겠습니까?")) {

        const $extensionElement = $(extensionElement); // jquery객체로

        const customExtension = $extensionElement.find('.extension-text').text().trim();
        $extensionElement.remove();

        $.ajax({
            url: "/api/custom-extensions/delete",
            method: "DELETE",
            data: { ceName: customExtension },
            success: function(response) {
                alert(response);
                getCount();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    }
}
