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
               // 파일 이름에서 확장자 추출
                const fileExtension = file.name.split('.').pop(); // asdf.jpg 에서 jpg

                if (!extensionsArray.includes(fileExtension)) {
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