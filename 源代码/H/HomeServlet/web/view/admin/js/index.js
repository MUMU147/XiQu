// 侧边二级导航显示
const list = document.querySelector('#first');
const menus = document.querySelectorAll('.accordion');
const uls = document.querySelectorAll('.btn-content')
for (let i = 0; i < menus.length; i++) {
    menus[i].addEventListener('click', function () {
        alinks = this.nextElementSibling.children;
        // console.log(alinks);
        if (this.className == 'accordion') {
            this.className = 'accordion active';
            for (let k = 0; k < alinks.length; k++) {
                alinks[k].style.height = '80px';
            }
        } else {
            this.className = 'accordion';
            for (let k = 0; k < alinks.length; k++) {
                alinks[k].style.height = '0';
                alinks[k].style.borderBottom = '0';
            }
        }
        for (let j = 0; j < menus.length; j++) { //排他思想
            if (j != i) {
                menus[j].className = 'accordion';
                const alist = menus[j].nextElementSibling.children;
                // console.log(alist);
                for (let k = 0; k < alist.length; k++) {
                    alist[k].style.height = '0';
                    alist[k].style.borderBottom = '0';
                }
            }
        }
    })
}

// a链接的点击效果
for (let i = 0; i < uls.length; i++) {
    uls[i].addEventListener('click', function () {
        ullinks = uls[i].children
        // console.log(ullinks);
        for (let j = 0; j < ullinks.length; j++) {
            if (ullinks[j].children.className === 'aclick') {
                for (let k = 0; k < ullinks.length; k++) {
                    ullinks[k].children.classList.remove('aclick')
                }
                ullinks[j].children.classList.add('aclick')
            } else {
                ullinks[j].children.className === 'aclick'
            }
        }
    })
}

// 新闻热点TOP
const ranklists = document.querySelectorAll('.content #first ul li')
// console.log(ranklists);
for (let i = 0; i < ranklists.length; i++) {
    let ranklist = ranklists[i].childre
    if (i == 0) {
        ranklists[i].style.height = '425px'
        ranklists[i].style.marginTop = '60px'
    } else {
        if (i == 2) {
            ranklists[i].style.height = '300px'
            ranklists[i].style.marginTop = '185px'
        }
    }
}

// 点击切换页面，不跳转链接
const title = document.querySelectorAll('#first .accordion');
const content = document.querySelectorAll('.content .tab');
// console.log(title);
// console.log(content);
for (let i = 0; i < title.length; i++) {
    title[i].addEventListener('click', function () {
        for (let j = 0; j < title.length; j++) {
            content[j].style.display = 'none'
            if (i === j) {
                content[i].style.display = 'block'
            }
        }
    })
}
// <!-- 用了ajax，避免刷新页面-->
let button = document.querySelector("#wo")
console.log("sssssssssss")
console.log(button.id)
button.addEventListener('click', function () {
    //window.location.href="getvcode";
    console.log("sssssssssss11111")
    let input = document.querySelector(".keyword").value;
    console.log("sssssssssss11111")
    var xhr = new XMLHttpRequest();//ajax实现局部刷新
    xhr.open('GET', '/HomeServlet/mypindex?keyword=' + input, true); // 第三个参数设置为true表示异步处理
    xhr.send();
    // alert("验证码已发送")
})






