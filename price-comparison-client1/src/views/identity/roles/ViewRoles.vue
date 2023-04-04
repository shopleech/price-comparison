<template>
    <div class="row">
        <div class="col-2"></div>
        <div class="col-10">
            <ul>
                <li v-for="item of limitArray(5)" :key="item.id">
                    {{ email }} - {{ roles }}
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
import { Vue } from 'vue-class-component'
import { useIdentityStore } from '@/stores/identity'
import Logger from '@/logger'

export default class EditRoles extends Vue {
    private logger = new Logger(EditRoles.name)
    private identityStore = useIdentityStore()
    private wasOk: boolean | null = null

    email = ''
    password = ''
    errorMsg: string | null = null
}
</script>

<style scoped>

</style>
