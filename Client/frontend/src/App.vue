<template>
  <div id="app">
    <Contacts/>
  </div>
</template>

<script>
import Contacts from './components/Contacts-component.vue'

export default {
  name: 'App',
  components: {
    Contacts
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>