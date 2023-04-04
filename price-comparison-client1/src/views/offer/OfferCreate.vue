<template>
    <div class="row">
        <div class="col-md-2 p-4">
        </div>
        <div class="col-md-10 p-4">
            <h4>Create offer</h4>

            <div v-if="errorMsg != null" class="text-danger validation-summary-errors" data-valmsg-summary="true">
                <ul>
                    <li>{{ errorMsg }}</li>
                </ul>
            </div>

            <div>
                <div class="form-group">
                    <form method="post" enctype="multipart/form-data">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="inputGroupFile01">Upload</label>
                            <input type="file" accept=".csv" class="form-control" id="inputGroupFile01"
                                   @change="handleFileUpload( $event )">
                        </div>
                    </form>
                </div>
                <div>
                    <table v-if="parsed" style="width: 100%;">
                        <thead>
                        <tr>
                            <th v-for="(header, key) in content.meta.fields"
                                v-bind:key="'header-'+key">
                                {{ header }}
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(row, rowKey) in content.data"
                            v-bind:key="'row-'+rowKey">
                            <td v-for="(column, columnKey) in content.meta.fields"
                                v-bind:key="'row-'+rowKey+'-column-'+columnKey">
                                <input v-model="content.data[rowKey][column]"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <br>
                    <!-- /*
                    <button v-on:click="submitFile()">Submit File</button>
                    */ -->
                    <button v-on:click="submitUpdates()">Submit Updates</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import HttpClient from '@/http-client'
import Papa from 'papaparse'
import { OfferService } from '@/services/OfferService'
import { useOfferStore } from '@/stores/offer'
import Logger from '@/logger'

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Options({
    components: {},
    props: {},
    emits: [],
})
export default class OfferCreate extends Vue {
    private logger = new Logger(OfferCreate.name)
    offerStore = useOfferStore()
    offerService = new OfferService()

    errorMsg: string | null = null
    uploadFile = ''
    content = []
    parsed = false

    async submitClicked (): Promise<void> {
        this.logger.info('submitClicked')
    }

    handleFileUpload (event: any) {
        this.uploadFile = event.target.files[0]
        this.parseFile()
    }

    parseFile () {
        Papa.parse(this.uploadFile, {
            header: true,
            skipEmptyLines: true,
            complete: (results: any) => {
                this.content = results
                this.parsed = true
            }
        })
    }

    async submitFile () {
        const formData = new FormData()

        formData.append('file', this.uploadFile)

        await HttpClient.post('/preview-file',
            formData,
            {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }
        )
    }

    async submitUpdates () {
        this.offerService.upload(this.content)
    }
}
</script>
