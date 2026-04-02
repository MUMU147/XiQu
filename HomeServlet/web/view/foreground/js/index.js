// 时间显示
  const nowtime = document.querySelector('.nowtime')
  //得到日期对象
  const date = new Date()
  const weekday=date.getDay()
  if(weekday==0){
    week="日"
  }else if(weekday==1){
    week="一"
  }else if(weekday==2){
    week="二"
  }else if(weekday==3){
    week="三"
  }else if(weekday==4){
    week="四"
  }else if(weekday==5){
    week="五"
  }else if(weekday==6){
    week="六"
  }
  //div.innerHTML = date.toLocaleString()   //年月日 时分秒
  setInterval(function () {
  const date = new Date()
    nowtime.innerHTML = date.toLocaleString()+" "+"星期"+week
})
  //div.innerHTML = date.toLocaleString()   //年月日 时分秒
  // div.innerHTML = date.toLocaleDateString()   //年月日
  // div.innerHTML = date.toLocaleTimeString()  //时分秒



//每隔一秒 隐藏四张 显示一张
// 每隔一秒 所有的隐藏 让其中一张显示 now 显示图片的下标
// now=0
// now++
let imgs = document.querySelectorAll('.banner img')
let lis = document.querySelectorAll('#logo li')
let prev=document.querySelector(".banner .toggle .prev")
let next=document.querySelector(".banner .toggle .next")
let now = 0
console.log(imgs)
function auto() {
  // 通过 for 循环隐藏所有 循环完找到now 显示
  for (let i = 0; i < imgs.length; i++) {
    imgs[i].style.display = 'none';
    lis[i].classList.remove('active')
  }
  imgs[now].style.display = 'block'
  lis[now].classList.add('active')
  now++
  if (now >= imgs.length) now = 0
}
let timer = setInterval(auto, 1500)
let banner = document.querySelector('.banner')
banner.addEventListener('mouseenter', function () {
  clearInterval(timer)
})
banner.addEventListener('mouseleave', function () {
  timer = setInterval(auto, 1000)
})
//通过循环给每个li添加事件监听
for (let i = 0; i < lis.length; i++) {
  lis[i].addEventListener('click', function () {
    // 隐藏所有图片 第i个图片显示
    for (let j = 0; j < imgs.length; j++) {
      imgs[j].style.display = 'none';
      lis[j].classList.remove('active')
    }
    now = i
    imgs[i].style.display = 'block'
    lis[now].classList.add('active')
  })
}





/* 手风琴 */
// 获取元素
const sfq = document.querySelectorAll('.kinds .kbox');// lis = [li, li, li, li, li]
// 分析：
// 1、鼠标进入显示图片，
for (let i = 0; i < sfq.length; i++) {

  sfq[i].addEventListener('mouseenter', function () {
    for (let j = 0; j < sfq.length; j++) {
      sfq[j].style.width = '139px';
    }
    this.style.width = '900px'
  })

  sfq[i].addEventListener('mouseleave', function () {
    // 让所有的li变成240
    for (let j = 0; j < sfq.length; j++) {
      sfq[j].style.width = '291px';
    }
  })
}


  // 第一大模块，页面滑动可以显示和隐藏
  (function () {
  // 获取元素
  const main = document.querySelector('.main')
  //console.log(main.offsetTop);
  const elevator = document.querySelector('.elevator')
  // 1. 当页面滚动大于  ,显示电梯导航
  // 2. 给页面添加滚动事件
  window.addEventListener('scroll', function () {
  //被卷去的头部大于
  const n = document.documentElement.scrollTop
    elevator.style.opacity = n >= main.offsetTop ? 1 : 0
})
  // 点击返回页面顶部
  const backTop = document.querySelector('#backTop')
  backTop.addEventListener('click', function () {
  document.documentElement.scrollTop = 0
})
})();

  // 第二第三都放到另外一个执行函数里面
  (function () {

  // 2. 点击页面可以滑动
  const list = document.querySelector('.elevator-list')
  list.addEventListener('click', function (e) {
  //console.log(11)
  //去掉 顶部这个li  && e.target.dataset.name
  if (e.target.tagName === 'A' && e.target.dataset.name) {
  // 排他思想
  // 先移除原来的类active
  // 先获取这个active的对象
  const old = document.querySelector('.elevator-list .active')
  // console.log(old)
  // 判断 如果原来有active类的对象，就移除类，如果开始就没有对象，就不删除，所以不报错
  if (old) old.classList.remove('active')
  // 当前元素添加 active
  e.target.classList.add('active')
  // 获得自定义属性 e.target.dataset  new   topic
  console.log(e.target.dataset.name)
  // 根据小盒子的自定义属性值 去选择 对应的大盒子
  // console.log(document.querySelector(`.main_${e.target.dataset.name}`).offsetTop)
  // 获得对应大盒子的 offsetTop
  const top = document.querySelector(`.main_${e.target.dataset.name}`).offsetTop+700
  // 让页面滚动到对应的位置
  document.documentElement.scrollTop = top
    window.scrollTo({
      top: top,
      behavior: 'smooth', // 使用平滑滚动效果
      duration: 300 // 指定滚动时间（毫秒）
    });
}
})


//   // 3. 页面滚动，可以根据大盒子选 小盒子 添加 active 类
    window.addEventListener('scroll', function () {
      // 3.1 先移除类
      // 先获取这个active的对象
      const old = document.querySelector('.elevator-list .active')
      // 判断 如果原来有active类的对象，就移除类，如果开始就没有对象，就不删除，所以不报错
      if (old) old.classList.remove('active')

      // 3.2 判断页面当前滑动的位置，选择小盒子
      // 获取大盒子
      const one = document.querySelector('.in')
      const two = document.querySelector('.delop')
      const three = document.querySelector('.kinds')
      const four = document.querySelector('.famous')
      const five=document.querySelector(".school")

      const n = document.documentElement.scrollTop

      if (n >= one.offsetTop && n < two.offsetTop) {
        // 选择第一个小盒子
        document.querySelector('[data-name=one]').classList.add('active')
      } else if (n >= two.offsetTop && n < three.offsetTop) {
        document.querySelector('[data-name=two]').classList.add('active')
      } else if (n >= three.offsetTop && n < four.offsetTop) {
        document.querySelector('[data-name=three]').classList.add('active')
      } else if  (n >= four.offsetTop && n < five.offsetTop) {
        document.querySelector('[data-name=four]').classList.add('active')
      }else if ( n >= five.offsetTop) {
        document.querySelector('[data-name=five]').classList.add('active')
      }
    })
})();
