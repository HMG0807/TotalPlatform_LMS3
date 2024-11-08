const dataTransfer = new DataTransfer();

$("#files").change(function(){
   
    let fileArr = document.getElementById("files").files

    if(fileArr != null && fileArr.length>0){

      // =====DataTransfer 파일 관리========
        for(var i=0; i<fileArr.length; i++){
            dataTransfer.items.add(fileArr[i])
        }
        document.getElementById("files").files = dataTransfer.files;
        console.log("dataTransfer =>",dataTransfer.files)
        console.log("input FIles =>", document.getElementById("files").files)
    // ==========================================
       
    }
     
})


// 파일 삭제

$("#fileList").click(function(event){
    let fileArr = document.getElementById("files").files
    if(event.target.className=='remove_button'){
        // console.log(event.target.dataset.index )
        targetFile = event.target.dataset.index 
        
      // ============DataTransfer================
        for(var i=0; i<dataTransfer.files.length; i++){
            if(dataTransfer.files[i].lastModified==targetFile){
                // 총용량에서 삭제
                total_file_size-=dataTransfer.files[i].size
                
                dataTransfer.items.remove(i)
                break
            }
        }
        document.getElementById("files").files = dataTransfer.files;
 
        const removeTarget = document.getElementById(targetFile);
        removeTarget.remove();
      
        console.log("dataTransfer 삭제후=>",dataTransfer.files)
        console.log('input FIles 삭제후=>',document.getElementById("files").files)

    }
})