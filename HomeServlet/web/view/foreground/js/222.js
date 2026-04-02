// tab 栏切换效果
// 获取元素
// var flis = document.querySelector('.tab_list').querySelectorAll('li');
var ftab_list = document.querySelector('.wenchuang .tab_list');
var flis = ftab_list.querySelectorAll('li');
var fitems = document.querySelectorAll('.wenchuang .tab_con .item');
// for 循环绑定点击事件
// console.log(ftab_list);
// console.log(flis);
// console.log(fitems);
for (var i = 0; i < flis.length; i++) {
  // 给小li设置索引号 
  flis[i].setAttribute('data-index', i);
  // 2.上面的模块选项卡
  flis[i].addEventListener('click', function () {
    // 干掉所有人 其余的li清除 class 这个类
    for (var i = 0; i < flis.length; i++) {
      flis[i].className = '';
      // console.log(i);
    }
    // 留下我自己
    this.className = 'current';

    // 2.下面的显示内容模块
    console.log(this);
    var findex = this.getAttribute('data-index');
    console.log(findex);
    // 干掉所有人 让其余的item 这些div隐藏
    for (var j = 0; j < fitems.length; j++) {
      fitems[j].style.display = 'none';
    }
    // 留下我自己 让对应的item 显示出来
    fitems[findex].style.display = 'block';

  })
}

var stab_list = document.querySelector('.xiqu .tab .tab_list');
var slis = stab_list.querySelectorAll('li');
var sitems = document.querySelectorAll('.xiqu .tab_con .item');
// for 循环绑定点击事件
for (var i = 0; i < slis.length; i++) {
  // 给小li设置索引号
  slis[i].setAttribute('data-index', i);
  // 2.上面的模块选项卡
  slis[i].addEventListener('click', function () {
    // 干掉所有人 其余的li清除 class 这个类
    for (var i = 0; i < slis.length; i++) {
      slis[i].className = '';
    }
    // 留下我自己
    this.className = 'current';

    // 2.下面的显示内容模块
    var sindex = this.getAttribute('data-index');
    // console.log(sindex);
    // 干掉所有人 让其余的item 这些div隐藏
    for (var j = 0; j < sitems.length; j++) {
      sitems[j].style.display = 'none';
    }
    // 留下我自己 让对应的item 显示出来
    sitems[sindex].style.display = 'block';

  })
}

var ttab_list = document.querySelector('.xiqus .tab_list');
var tlis = ttab_list.querySelectorAll('li');
var titems = document.querySelectorAll('.xiqus .tab_con .item');
for (var i = 0; i < tlis.length; i++) {
  tlis[i].setAttribute('data-index', i);
  tlis[i].addEventListener('click', function () {
    for (var i = 0; i < tlis.length; i++) {
      tlis[i].className = '';
    }
    this.className = 'current';

    var tindex = this.getAttribute('data-index');
    // console.log(tindex);
    for (var j = 0; j < titems.length; j++) {
      titems[j].style.display = 'none';
    }

    titems[tindex].style.display = 'block';
  })
}



