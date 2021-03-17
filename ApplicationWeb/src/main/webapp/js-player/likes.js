$(document).ready(() =>{
    $(':checkbox').change((event)=>{
        const value = event.target.checked;
        $.post("like", {data : value});
    });
});