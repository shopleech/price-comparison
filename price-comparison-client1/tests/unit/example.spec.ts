import { mount, shallowMount } from '@vue/test-utils'
import HelloWorld from '@/components/HelloWorld.vue'

describe('HelloWorld.vue', () => {
    it('renders props.msg when passed', () => {
        const msg = 'new message'
        const wrapper = shallowMount(HelloWorld, {
            props: { msg }
        })
        expect(wrapper.text()).toMatch(msg)
    })
})

// The component to test
const MessageComponent = {
    template: '<p>{{ msg }}</p>',
    props: ['msg']
}

test('displays message', () => {
    const wrapper = mount(MessageComponent, {
        props: {
            msg: 'Hello world'
        }
    })

    // Assert the rendered text of the component
    expect(wrapper.text()).toContain('Hello world')
})
