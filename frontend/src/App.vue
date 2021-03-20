<template>
    <div id="app">
        <!-- <img src="./assets/logo.png"> -->
        <div>
            <Header></Header>
            <router-view></router-view>
        </div>
    </div>
</template>


<script>
import Header from '@/components/header/Header'

export default {
    name: 'App',
    components: {
        'Header': Header
    },
    computed: {},
}
</script>


<style>
@import './assets/app.css';
@import './assets/draft.css';

#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>