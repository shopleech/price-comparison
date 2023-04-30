<template>
    <div id="qr-code-full-region"></div>
</template>

<script>
import { Options, Vue } from 'vue-class-component'
import { Html5QrcodeScanner } from 'html5-qrcode'

/**
 * @author Ahto Jalak
 * @since 15.04.2023
 */
@Options({
    components: {},
    methods: {
        onScanSuccess (decodedText, decodedResult) {
            this.$emit('result', decodedText, decodedResult);
        }
    },
    props: {
        qrbox: {
            type: Number,
            default: 100
        },
        fps: {
            type: Number,
            default: 10
        },
    },
})
export default class BarcodeScanner extends Vue {
    mounted () {
        const config = {
            fps: this.fps,
            qrbox: this.qrbox,
        }
        const html5QrcodeScanner = new Html5QrcodeScanner('qr-code-full-region', config)
        html5QrcodeScanner.render(this.onScanSuccess)
    }
}
</script>

<style scoped>

</style>
