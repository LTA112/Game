let currentFormId = null; // Biến lưu ID của form hiện tại
var checkUsername = false;
var checkPassword1 = false;
var checkPassword2 = false;
var checkNickname = false;

function showForm(formId) {
    const form = document.getElementById(formId);

    // Nếu form hiện tại đang được hiển thị và không phải là form mới được ấn
    if (currentFormId && currentFormId !== formId) {
        const currentForm = document.getElementById(currentFormId);
        currentForm.classList.add('hidden'); // Ẩn form hiện tại
    }

    // Kiểm tra trạng thái hiện tại của form
    if (!form.classList.contains('hidden')) {
        // Nếu form đang hiển thị, thu lại nó
        form.classList.add('hidden');
        currentFormId = null; // Đặt lại biến lưu ID form hiện tại
    } else {
        // Nếu form đang ẩn, hiển thị nó
        form.classList.remove('hidden');
        currentFormId = formId; // Lưu ID của form hiện tại
    }
}



// Hàm kiểm tra đầu vào hợp lệ (chỉ chữ cái và số, không có dấu cách)
function isValidInput(input, minLength) {
    // Loại bỏ các khoảng trắng ở đầu và cuối chuỗi
    var trimmedInput = input.trim();

    // Kiểm tra xem chuỗi đã trim có chứa dấu cách hay không
    if (input !== trimmedInput) {
        return false; // Nếu có dấu cách, trả về false
    }

    // Biểu thức chính quy để kiểm tra chỉ chứa chữ cái và số
    var regex = /^[a-zA-Z0-9]+$/;

    // Kiểm tra điều kiện độ dài và ký tự
    return regex.test(input) && input.length >= minLength;
}
// 2 ham ngau loi
//------------------------------------------------------------------------

// Hàm kiểm tra mật khẩu
function checkPassWordKeyup() {
    checkPassword1 = false;
    var password = document.getElementById('registerPassword').value;
    var passwordError = document.getElementById('passwordError');

    if (!isValidInput(password, 3)) {
        passwordError.style.display = 'inline';
    } else {
        passwordError.style.display = 'none';
        checkPassword1 = true;
    }
}

// Hàm kiểm tra mật khẩu thứ 2 phải trùng khớp
function validatePasswords() {
    checkPasswor2 = false;
    var pass1 = document.getElementById('registerPassword').value;
    var pass2 = document.getElementById('registerConfirmPassword').value;
    var passwordNotMatch = document.getElementById('passwordNotMatch');

    if (pass1 !== pass2) {
        passwordNotMatch.style.display = 'inline';
    } else {
        passwordNotMatch.style.display = 'none';
        checkPassword2 = true;
    }
}

// Hàm kiểm tra username khi người dùng nhập vào ô username (keyup event)
// Nó chỉ đóng vai trò hiện cảnh báo trùng. Hết
function checkUsernameKeyup() {
    checkUsername = false;
    var username = document.getElementById('registerUserName').value;
    var usernameError = document.getElementById('usernameError');

    if (!isValidInput(username, 6)) {
        usernameError.style.display = 'inline';
    } else {
        usernameError.style.display = 'none';
        checkUsername = true;
    }

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/RegisterController', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            var usernameTaken = document.getElementById('usernameTaken');

            if (response.exists) {
                console.log('Server response: the username exists');
                usernameTaken.style.display = 'inline';
            } else {
                console.log('Server response: Username is not exist, ok to use');
                usernameTaken.style.display = 'none';
                checkUsername = true;
            }
        }
    };
    xhr.send('username=' + encodeURIComponent(username));
}

// Hàm kiểm tra username khi người dùng nhập vào ô username (keyup event)
// Nó chỉ đóng vai trò hiện cảnh báo trùng. Hết
function checkNicknameKeyup() {
    checkNickname = false;
    var nickname = document.getElementById('registerNickname').value;
    var nicknameError = document.getElementById('nicknameError');

    if (!isValidInput(nickname, 6)) {

        nicknameError.style.display = 'inline';
    } else {
        nicknameError.style.display = 'none';
    }

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/RegisterController', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            var nicknameTaken = document.getElementById('nicknameTaken');

            if (response.exists) {
                console.log('Server response: the nickname is exists');
                nicknameTaken.style.display = 'inline';
            } else {
                console.log('Server response: Nickname is not exist, ok to use');
                nicknameTaken.style.display = 'none';
                checkNickname = true;
            }
        }
    };
    xhr.send('nickname=' + encodeURIComponent(nickname));
}
//------------------------------------------------------------------------

function validateForm() {
    if (checkUsername && checkPassword2 && checkNickname) {
        return true;
    } else {
        return false;
    }

}
    