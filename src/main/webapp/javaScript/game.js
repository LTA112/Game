let move_speed = 2.5, gravity = 0.25;
let bird = document.querySelector('.bird');
let img = document.getElementById('bird-1');



// Đặt đường dẫn hình ảnh
img.src = basePath + `/Bird.png`;
let bird2 = basePath + `/Bird-2.png`;


let bird_props = bird.getBoundingClientRect();
let background = document.querySelector('.background').getBoundingClientRect();
let score_val = document.querySelector('.score_val');
let message = document.querySelector('.message');
let score_title = document.querySelector('.score_title');

let game_state = 'Start';
let bird_dy = 0;
let pipe_separation = 0;
let pipe_gap = 45;
img.style.display = 'none';
message.classList.add('messageStyle');

function fetchAccountId() {
    fetch('/SessionDataServlet')
            .then(response => response.json())
            .then(data => {
                const accountId = data.accountId;
                if (accountId) {
                    console.log('tao co Account ID:', accountId);
                    window.accountId = accountId; // Lưu accountId vào biến toàn cục
                    getHighScore(accountId); // Lấy điểm cao với accountId
                } else {
                    console.error('No account ID found');
                }
            })
            .catch(error => {
                console.error('Error fetching account ID:', error);
            });
}

function getHighScore(accountId) {
    fetch(`/ScoreController?accountId=${accountId}`)
            .then(response => response.json())
            .then(data => {
                console.log('tao co High Score:', data.score);
                updateGameHighScore(data.score); // Cập nhật điểm cao trong trò chơi
            })
            .catch(error => {
                console.error('Error fetching high score:', error);
            });
}

function updateGameHighScore(highScore) {
    score_title.innerHTML = `High Score: ${highScore}`;
}
fetchAccountId();

function updateHighScore(highScore, accountId) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/ScoreController', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log('Server response:', xhr.responseText);
            } else {
                console.error('Error:', xhr.status);
            }
        }
    };
    xhr.send(`highScore=${encodeURIComponent(highScore)}&accountId=${encodeURIComponent(accountId)}`);
}

document.addEventListener('DOMContentLoaded', () => {
    displayHighScore();
});

document.addEventListener('keydown', (e) => {
    if (e.key === ' ' && game_state !== 'Play') {
        document.querySelectorAll('.pipe_sprite').forEach((e) => {
            e.remove();
        });
        img.style.display = 'block';
        bird.style.top = '40vh';
        bird_dy = 0;  // Reset bird_dy
        game_state = 'Play';
        message.innerHTML = '';
        score_val.innerHTML = '0';
        message.classList.remove('messageStyle');
        play();
    }
    if (e.key === 'Enter' && game_state === 'End') {
        document.querySelectorAll('.pipe_sprite').forEach((e) => {
            e.remove();
        });
        img.style.display = 'block';
        bird.style.top = '40vh';
        bird_dy = 0;  // Reset bird_dy
        game_state = 'Play';
        message.innerHTML = '';
        score_val.innerHTML = '0';
        message.classList.remove('messageStyle');
        play();
    }
});

function play() {
    function move() {
        if (game_state !== 'Play')
            return;

        let pipe_sprite = document.querySelectorAll('.pipe_sprite');
        pipe_sprite.forEach((element) => {
            let pipe_sprite_props = element.getBoundingClientRect();
            bird_props = bird.getBoundingClientRect();

            if (pipe_sprite_props.right <= 0) {
                element.remove();
            } else {
                if (bird_props.left < pipe_sprite_props.left + pipe_sprite_props.width &&
                        bird_props.left + bird_props.width > pipe_sprite_props.left &&
                        bird_props.top < pipe_sprite_props.top + pipe_sprite_props.height &&
                        bird_props.top + bird_props.height > pipe_sprite_props.top) {

                    let currentScore = parseInt(score_val.innerHTML, 10);
                    let highScore = parseInt(score_title.innerHTML.replace('High Score: ', ''), 10);
                    if (isNaN(highScore) || currentScore > highScore) {
                        updateHighScore(currentScore, accountId);
                        score_title.innerHTML = `High Score: ${currentScore}`;
                    }

                    game_state = 'End';
                    message.innerHTML = 'Game Over'.fontcolor('red') + '<br>Score: ' + score_val.innerHTML + '<br>' + score_title.innerHTML;
                    message.classList.add('messageStyle');
                    img.style.display = 'none';
                
                    return;

                } else if (bird_props.top <= 0 || bird_props.bottom >= background.bottom) {

                    let currentScore = parseInt(score_val.innerHTML, 10);
                    let highScore = parseInt(score_title.innerHTML.replace('High Score: ', ''), 10);
                    if (isNaN(highScore) || currentScore > highScore) {
                        updateHighScore(currentScore, accountId);
                        score_title.innerHTML = `High Score: ${currentScore}`;
                    }

                    game_state = 'End';
                    message.innerHTML = 'Game Over'.fontcolor('red') + '<br>Score: ' + score_val.innerHTML + '<br>' + score_title.innerHTML;
                    message.classList.add('messageStyle');
                    img.style.display = 'none';
                    
                    return;
                } else {
                    if (pipe_sprite_props.right < bird_props.left && pipe_sprite_props.right + move_speed >= bird_props.left &&
                            element.increase_score === '1') {
                        score_val.innerHTML = +score_val.innerHTML + 1;
                    }
                    element.style.left = pipe_sprite_props.left - move_speed + 'px';
                }
            }
        });
        requestAnimationFrame(move);
    }
    requestAnimationFrame(move);

    function apply_gravity() {
        if (game_state !== 'Play')
            return;
        bird_dy += gravity;

        // Register keydown and keyup events once
        document.addEventListener('keydown', (e) => {
            if (e.key === 'ArrowUp') {
                img.src = basePath + '/Bird-2.png';
                bird_dy = -7;
            }
        }, {once: true});

        document.addEventListener('keyup', (e) => {
            if (e.key === 'ArrowUp') {
                img.src = basePath + '/Bird.png';
            }
        }, {once: true});

        bird.style.top = bird_props.top + bird_dy + 'px';
        bird_props = bird.getBoundingClientRect();
        requestAnimationFrame(apply_gravity);
    }
    requestAnimationFrame(apply_gravity);

    function create_pipe() {
        if (game_state !== 'Play')
            return;

        if (pipe_separation > 115) {
            pipe_separation = 0;

            let pipe_posi = Math.floor(Math.random() * 43) + 8;
            let pipe_sprite_inv = document.createElement('div');
            pipe_sprite_inv.className = 'pipe_sprite';
            pipe_sprite_inv.style.top = pipe_posi - 70 + 'vh';
            pipe_sprite_inv.style.left = '100vw';

            document.body.appendChild(pipe_sprite_inv);
            let pipe_sprite = document.createElement('div');
            pipe_sprite.className = 'pipe_sprite';
            pipe_sprite.style.top = pipe_posi + pipe_gap + 'vh';
            pipe_sprite.style.left = '100vw';
            pipe_sprite.increase_score = '1';

            document.body.appendChild(pipe_sprite);
        }
        pipe_separation++;
        requestAnimationFrame(create_pipe);
    }
    requestAnimationFrame(create_pipe);
}

