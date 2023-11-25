// 파일 확장자 차단
function validateFile(fileInput) {

    // 파일 확장자 테이블의 컬럼 가져오기
    $.ajax({
        type: 'GET',
        url: '/api/file-extensions/get-allowed-extensions',
        success: function (response) {
            const allowedExtensions = response.allowedExtensions; // "jpg|bmp|jpeg|png|exe"

            // 파일 유효성 검사
            const file = fileInput.files[0];

            if (file) {

                const extensionsArray = allowedExtensions.split('|');
                const escapedExtensions = extensionsArray.map(extension => `.${extension}`).join('|'); // .jpg|.bmp|.jpeg 로 만들기

                const extensionRegExp = new RegExp(`(${escapedExtensions})$`, 'i');

                if (!extensionRegExp.test(file.name)) {
                    alert("허락된 확장자 파일입니다.");
                } else {
                    fileInput.value = "";
                    alert(`차단된 확장자 파일입니다.`);
                }
            }

        },
        error: function (error) {
            console.error(error);
        }
    });
}