<template>
    <div class="modal">
        <div class="overlay" @click="$emit('close')"></div>
        <div class="modal-card">
            <slot />
            <div style="padding-top:20px;"><button @click="$emit('close')">닫기</button></div>
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style>
/* Modal */

.modal,
.overlay {
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    top: 0;
}

.overlay {
    opacity: 0.5;
    background-color: black;
}

.modal-card {
    position: relative;
    width: 504px;
    margin: auto;
    margin-top: 30px;
    padding: 20px;
    background-color: white;
    min-height: 500px;
    z-index: 10;
    opacity: 1;
    top: 100px;
}
</style>