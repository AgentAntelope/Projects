#include <linux/fs.h>
#include <linux/init.h>
#include <linux/miscdevice.h>
#include <linux/module.h>
#include <linux/random.h>
#include <asm/uaccess.h>


static char cardsDealt[52];
static ssize_t card_read(struct file * file, char * buf, 
			  size_t count, loff_t *ppos)
{
	if (count <= 0)
		return -EINVAL;
	//if (copy_to_user(buf, , len))
	//	return -EINVAL;
	buf = get_random_card();
	*ppos = 1;

	return 1;
}
void zero_array(){
	int i = 0;
	for(i = 0; i < 52; i++){
		cardsDealt[i] = 0;
	}
}
unsigned char get_random_byte(int max) {
         unsigned char c;
         get_random_bytes(&c, 1);
         return c%max;
}
unsigned char send_random_card(){
	unsigned char card_index, card;
	char index = 0;
	card_index = get_random_byte(52);
	while(7){
	if(cardsDealt[card_index] == 0){
		cardsDealt[card_index] == 1
		return card_index;
	}
	if(index > 52){
		zero_array();
		index = 0;
	}
	index++;
	card_index++;
	card_index = card_index % 52;
	}
}

static const struct file_operations card_fops = {
	.owner		= THIS_MODULE,
	.read		= card_read,
};

static struct miscdevice card_dev = {
	MISC_DYNAMIC_MINOR,
	"card",
	&card_fops
};

static int __init
hello_init(void)
{
	int ret;

	/*
	 * Create the "hello" device in the /sys/class/misc directory.
	 * Udev will automatically create the /dev/hello device using
	 * the default rules.
	 */
	ret = misc_register(&card_dev);
	if (ret)
		printk(KERN_ERR "Unable to register \"Hello, world!\" misc device\n");

	return ret;
}

module_init(hello_init);

static void __exit
hello_exit(void)
{
	misc_deregister(&card_dev);
}

module_exit(hello_exit);

MODULE_LICENSE("GPL");

MODULE_DESCRIPTION("\"Hello, world!\" minimal module");
MODULE_VERSION("dev");
