package com.ForgeEssentials.afterlife;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class InventroyGrave extends InventoryBasic
{
	private Grave	grave;

	public InventroyGrave(Grave grave)
	{
		super(grave.owner + "'s grave.", grave.getSize());
		this.grave = grave;
	}

	@Override
	public void openChest()
	{
		for (int i = 0; i < getSizeInventory(); i++)
		{
			setInventorySlotContents(i, (ItemStack) null);
		}

		for (int i = 0; i < grave.inv.length; i++)
		{
			setInventorySlotContents(i, grave.inv[i].copy());
		}

		super.openChest();
	}

	@Override
	public void closeChest()
	{
		List<ItemStack> list = new ArrayList<ItemStack>();
		for (ItemStack is : inventoryContents)
		{
			if (is != null)
			{
				list.add(is);
			}
		}
		grave.inv = list.toArray(new ItemStack[list.size()]);

		grave.checkGrave();
		super.closeChest();
	}
}