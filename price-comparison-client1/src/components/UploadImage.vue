<template>
    <div>
        <div class="row">
            <div class="col-8">
                <label class="btn btn-default p-0">
                    <input
                        type="file"
                        accept="image/*"
                        ref="file"
                        @change="selectImage"
                    />
                </label>
            </div>

            <div class="col-4">
                <button
                    class="btn btn-success btn-sm float-right"
                    :disabled="!currentImage"
                    @click="upload"
                >
                    Upload
                </button>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <div v-if="currentImage" class="progress">
                    <div
                        class="progress-bar progress-bar-info"
                        role="progressbar"
                        :aria-valuenow="progress"
                        aria-valuemin="0"
                        aria-valuemax="100"
                        :style="{ width: progress + '%' }"
                    >
                        {{ progress }}%
                    </div>
                </div>

                <div v-if="previewImage">
                    <div>
                        <img class="preview my-3" style="max-width: 100%;" :src="previewImage" alt="" />
                    </div>
                </div>

                <div v-if="message" class="alert alert-secondary" role="alert">
                    {{ message }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { Options, Vue } from 'vue-class-component'
import { OfferService } from '@/bll/service/OfferService'

/**
 * @author Ahto Jalak
 * @since 14.05.2023
 */
@Options({
    components: {},
    props: {
        barcode: String,
    },
    data() {
        return {
            currentImage: undefined,
            previewImage: undefined,

            progress: 0,
            message: "",

            imageInfos: [],
        };
    },
    methods: {
        selectImage () {
            this.currentImage = this.$refs.file.files.item(0)
            this.previewImage = URL.createObjectURL(this.currentImage)
            this.progress = 0
            this.message = ''
        },
        upload () {
            this.progress = 0

            this.offerService.upload(this.barcode, this.currentImage, (event) => {
                this.progress = Math.round((100 * event.loaded) / event.total)
            })
                .then((response) => {
                    this.message = response.data.message
                })
                .then((images) => {
                    this.imageInfos = images.data
                })
                .catch((err) => {
                    this.progress = 0
                    this.message = 'Could not upload the image! ' + err
                    this.currentImage = undefined
                })
        },
    },
})
export default class UploadImage extends Vue {
    offerService = new OfferService()
}
</script>

<style scoped>

</style>
