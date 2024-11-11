// 다크모드 설정
const checkBox = document.querySelector('.check');
const setting_ConAll = document.querySelectorAll(".setting_Con"); 
const isUserColorTheme = localStorage.getItem('color-theme');
const isOsColorTheme = window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
const getUserTheme = () => (isUserColorTheme ? isUserColorTheme : isOsColorTheme);

const settingBtn = document.querySelector(".settingBtn");
const settingContainer = document.querySelector(".settingContainer");

settingBtn.addEventListener('click',function(e){
    e.preventDefault;

    if(settingContainer.classList.contains('on')){
        settingContainer.classList.remove('on');
        //checkBox.disabled = true;
        setting_ConAll.forEach(item =>{
            item.disabled = true;
        });
    }else{
        settingContainer.classList.add('on');
        //checkBox.disabled = false;
        setting_ConAll.forEach(item =>{
            item.disabled = false;
        });
    }
});

window.onload = function(){
    if(getUserTheme == 'dark'){
        localStorage.setItem('color-theme', 'dark');
        document.documentElement.setAttribute('color-theme','dark');
        checkBox.setAttribute('checked', true);
    }else{
        localStorage.setItem('color-theme', 'light');
        document.documentElement.setAttribute('color-theme','light');
    }
};


checkBox.addEventListener('click', e =>{
    if(e.target.checked){
        localStorage.setItem('color-theme', 'dark');
        document.documentElement.setAttribute('color-theme', 'dark');
    }else{
        localStorage.setItem('color-theme', 'light');
        document.documentElement.setAttribute('color-theme', 'light');
    }
});

